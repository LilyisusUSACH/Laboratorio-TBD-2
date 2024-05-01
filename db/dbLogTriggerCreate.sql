-- Para las pruebas se ocupan los drops, asi no se generan
-- problemas de tener tablas con distintos atributos
DROP TABLE IF EXISTS consulta_log;

CREATE TABLE consulta_log (
    id SERIAL PRIMARY KEY,
    tipo_consulta TEXT,
	filas_afectadas TEXT,
	tabla TEXT,
	usuario TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE FUNCTION registrar_consulta()
RETURNS TRIGGER AS $$
DECLARE
    operation_text TEXT;
    affected_row_text TEXT;
	actualUser TEXT;
BEGIN
    -- Ver que datos guardar para cada tipo de consulta
	BEGIN
	actualUser := (SELECT username FROM usuario_actual);
	EXCEPTION WHEN undefined_table THEN
        actualUser := 'no_user_logged';
    IF actualUser is NULL THEN
        RAISE EXCEPTION 'No se encontró ningún usuario actual';
		actualUser := 'no_user_logged';
	END IF;
	END;

    IF TG_OP = 'INSERT' THEN
        operation_text := 'INSERT';
        affected_row_text := NEW::TEXT;
    ELSIF TG_OP = 'UPDATE' THEN
        operation_text := 'UPDATE';
        affected_row_text := OLD::TEXT || ' => ' || NEW::TEXT;
    ELSIF TG_OP = 'DELETE' THEN
        operation_text := 'DELETE';
        affected_row_text := OLD::TEXT;
    ELSE
        RAISE EXCEPTION 'Operación desconocida detectada';
    END IF;
    INSERT INTO consulta_log (tipo_consulta, tabla, filas_afectadas, usuario) 
	VALUES (operation_text,TG_TABLE_NAME, affected_row_text, actualUser);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DO $$
DECLARE
    table_record RECORD;
BEGIN
    -- Recorre todas las tablas
    FOR table_record IN 
        SELECT table_name
        FROM information_schema.tables
        WHERE table_schema = 'public' AND table_type = 'BASE TABLE'
    LOOP
		-- Verificamos que la tabla no sea la de consulta
		-- Pq si no se genera un loop infinito jaja
	    IF table_record.table_name != 'consulta_log' THEN

			-- Verificar si el trigger ya existe y si existe eliminarlo
			IF EXISTS (
				SELECT 1
				FROM pg_trigger
				WHERE tgname = 'registro_consulta' AND tgrelid = (SELECT oid FROM pg_class WHERE relname = table_record.table_name)
			) THEN
				EXECUTE 'DROP TRIGGER IF EXISTS registro_consulta ON ' || table_record.table_name;
			END IF;

			-- Crear el trigger para la tabla q este en el loop
			EXECUTE 'CREATE TRIGGER registro_consulta
					 AFTER INSERT OR UPDATE OR DELETE ON public.' || table_record.table_name ||
					' FOR EACH ROW EXECUTE FUNCTION registrar_consulta()';
		END IF;
	END LOOP;
END $$;




