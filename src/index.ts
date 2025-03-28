import app from "./app";
import { AppDataSource } from './db/conexion'
import 'dotenv/config'

async function main() {
  try {
    await AppDataSource.initialize();
    console.log('Base de datos conectada');
    app.listen(process.env.PORT, () => {
      console.log("Servidor activo");
    });
  } catch(err) {
    if(err instanceof Error)
      console.log(err.message)
  }
  
}

main()