CREATE OR REPLACE PROCEDURE get_top_usuarios_queries()
AS $$
DECLARE
    usuario_log TEXT;
    tipo_consulta_log TEXT;
    cantidad_queries INTEGER;
    consultas TEXT;
BEGIN
    FOR usuario_log, tipo_consulta_log, cantidad_queries, consultas IN
        SELECT usuario, tipo_consulta, COUNT(*) AS cantidad_queries,
               STRING_AGG('[' || tipo_consulta || '] ' || filas_afectadas, E'\n' ORDER BY fecha DESC) AS consultas
        FROM consulta_log
        WHERE tipo_consulta IN ('INSERT', 'UPDATE', 'DELETE')
        GROUP BY usuario, tipo_consulta
        ORDER BY cantidad_queries DESC
    LOOP
        -- Mostrar los resultados obtenidos
        RAISE NOTICE 'Usuario: %, Tipo de Consulta: %, Cantidad de Queries: %, Consultas: %',
                     usuario_log, tipo_consulta_log, cantidad_queries, consultas;
    END LOOP;
END;
$$ LANGUAGE plpgsql;
