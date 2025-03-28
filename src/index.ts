import express from 'express'
import cors from 'cors'
import morgan from 'morgan';

const app = express();

let PORT = process.env.PORT || 3000;

app.listen(PORT, () => {
    console.log('Servidor activo');
})