import { DataSource } from "typeorm";
import 'dotenv/config'
import { Estudiante } from "../models/estudianteModel";
import { Profesor } from "../models/profesoresModel";
import { Curso } from "../models/cursosModel";

export const AppDataSource = new DataSource({
    type: "mysql",
    host: process.env.DB_HOST,
    port: parseInt(process.env.DB_PORT || '3306'),
    username: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_DATABASE,
    logging: true,
    entities: [Estudiante, Profesor, Curso],
    synchronize: false
})