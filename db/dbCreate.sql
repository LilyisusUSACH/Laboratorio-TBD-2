DROP TABLE IF EXISTS "vol_habilidad";
DROP TABLE IF EXISTS "ranking";
DROP TABLE IF EXISTS "tarea_habilidad";
DROP TABLE IF EXISTS "tarea";
DROP TABLE IF EXISTS "estado_tarea";
DROP TABLE IF EXISTS "estado_emergencia";
DROP TABLE IF EXISTS "eme_habilidad";
DROP TABLE IF EXISTS "emergencia";
DROP TABLE IF EXISTS "estado";
DROP TABLE IF EXISTS "habilidad";
DROP TABLE IF EXISTS "institucion";
DROP TABLE IF EXISTS "voluntario";
DROP TABLE IF EXISTS "usuarios_roles";
DROP TABLE IF EXISTS "usuarios";
DROP TABLE IF EXISTS "roles";

CREATE TABLE "roles" (
                         "id" SERIAL PRIMARY KEY,
                         "name" varchar UNIQUE NOT NULL
);

CREATE TABLE "usuarios" (
                            "id" SERIAL PRIMARY KEY,
                            "username" varchar UNIQUE NOT NULL,
                            "password" varchar NOT NULL, -- almacenada como un hash
                            "email" varchar UNIQUE NOT NULL,
                            "rol_id" bigint REFERENCES roles-- esto puede referenciar otra tabla de 'roles' si se utiliza un enfoque más normalizado
);

CREATE TABLE "voluntario" (
                              "id_voluntario" SERIAL PRIMARY KEY,
                              "user_id" INT REFERENCES "usuarios" ("id"),
                              "nombre" varchar,
                              "edad" int,
                              "direccion" varchar,
                              "genero" varchar,
                              "email" varchar,
                              "telefono" varchar,
                               "rut" varchar
);

CREATE TABLE "vol_habilidad" (
                                 "id_vol_habilidad" SERIAL PRIMARY KEY,
                                 "id_voluntario" bigint,
                                 "id_habilidad" bigint,
                                 "nivel_proficiencia" int
);

CREATE TABLE "tarea" (
                         "id_tarea" SERIAL PRIMARY KEY,
                         "descripcion" varchar,
                         "id_emergencia" bigint,
                         "id_estado" bigint
);

CREATE TABLE "tarea_habilidad" (
                                   "id_tarea_habilidad" SERIAL PRIMARY KEY,
                                   "id_tarea" bigint,
                                   "id_habilidad" bigint
);

CREATE TABLE "ranking" (
                           "id_ranking" SERIAL PRIMARY KEY,
                           "id_voluntario" bigint,
                           "id_tarea" bigint,
                           "puntuacion" int,
                            "participa" int
);

CREATE TABLE "institucion" (
                               "id_institucion" SERIAL PRIMARY KEY,
                               "nombre" varchar,
                               "email" varchar,
                               "telefono" varchar,
                               "ubicacion" varchar
);

CREATE TABLE "habilidad" (
                             "id_habilidad" SERIAL PRIMARY KEY,
                             "nombre" varchar,
                             "descripcion" varchar,
                             "certificacion_requerida" int
);

CREATE TABLE "estado" (
                                "id_estado" SERIAL PRIMARY KEY,
                                "descripcion" varchar
);

CREATE TABLE "emergencia" (
                              "id_emergencia" SERIAL PRIMARY KEY,
                              "nombre" varchar,
                              "descripcion" varchar,
                              "fecha_inicio" Date,
                              "fecha_fin" Date,
                              "id_institucion" bigint,
                              "id_estado" bigint
);

CREATE TABLE "eme_habilidad" (
                                 "id_eme_habilidad" SERIAL PRIMARY KEY,
                                 "id_emergencia" bigint,
                                 "id_habilidad" bigint
);

ALTER TABLE "ranking" ADD FOREIGN KEY ("id_voluntario") REFERENCES "voluntario" ("id_voluntario");

ALTER TABLE "ranking" ADD FOREIGN KEY ("id_tarea") REFERENCES "tarea" ("id_tarea");

ALTER TABLE "vol_habilidad" ADD FOREIGN KEY ("id_voluntario") REFERENCES "voluntario" ("id_voluntario");

ALTER TABLE "vol_habilidad" ADD FOREIGN KEY ("id_habilidad") REFERENCES "habilidad" ("id_habilidad");

ALTER TABLE "eme_habilidad" ADD FOREIGN KEY ("id_habilidad") REFERENCES "habilidad" ("id_habilidad");

ALTER TABLE "eme_habilidad" ADD FOREIGN KEY ("id_emergencia") REFERENCES "emergencia" ("id_emergencia");

ALTER TABLE "tarea_habilidad" ADD FOREIGN KEY ("id_tarea") REFERENCES "tarea" ("id_tarea");

ALTER TABLE "tarea_habilidad" ADD FOREIGN KEY ("id_habilidad") REFERENCES "habilidad" ("id_habilidad");

ALTER TABLE "tarea" ADD FOREIGN KEY ("id_estado") REFERENCES "estado" ("id_estado");

ALTER TABLE "emergencia" ADD FOREIGN KEY ("id_estado") REFERENCES "estado" ("id_estado");

ALTER TABLE "emergencia" ADD FOREIGN KEY ("id_institucion") REFERENCES "institucion" ("id_institucion");

ALTER TABLE "tarea" ADD FOREIGN KEY ("id_emergencia") REFERENCES "emergencia" ("id_emergencia");
