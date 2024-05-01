CREATE OR REPLACE FUNCTION obtener_top_usuarios_queries()
RETURNS TABLE(usuario TEXT, tipo_consulta TEXT, cantidad_queries BIGINT, consultas TEXT)
as $$
#variable_conflict use_column
BEGIN
  RETURN query
	SELECT usuario as user, tipo_consulta as query, COUNT(*) AS cantidad_queries,
	           STRING_AGG('[' || tipo_consulta || '] ' || filas_afectadas, E' \n ' ORDER BY fecha DESC) AS consultas
	    FROM consulta_log
	    WHERE tipo_consulta IN ('INSERT', 'UPDATE', 'DELETE')
	    GROUP BY usuario, tipo_consulta
	    ORDER BY cantidad_queries DESC;
END;
$$ LANGUAGE plpgsql;