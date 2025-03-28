import express, { Response, Request } from 'express'
import cors from 'cors'
import morgan from 'morgan';
import estudiantesRoutes from './routes/estudiantesRoutes'
import cursosRoutes from './routes/cursosRoutes'
import profesoresRoutes from './routes/profesoresRoutes'

const app = express();
let PORT =  3000;

app.use(cors());
app.use(morgan('dev'));

app.use('/estudiantes', estudiantesRoutes)
app.use('/cursos', cursosRoutes)
app.use('/profesores', profesoresRoutes)

app.get('/', (req: Request, res: Response) => {
    res.send('Hola mundo desde la ruta principal');
})



export default app;