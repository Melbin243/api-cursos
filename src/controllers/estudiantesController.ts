import { Request, Response } from "express";

class EstudiantesController {
  constructor() {}

  consultar(req: Request, res: Response){
    try {
        res.send('Consultar estudiantes');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  ingresar(req: Request, res: Response) {
    try {
        res.send('Ingresar estudiantes');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  consultarDetalle(req: Request, res: Response){
    const {id} = req.params;
    try {
        res.send('Consultar estudiante');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  actualizar(req: Request, res: Response){
    const {id} = req.params;
    try {
        res.send('Actualizar estudiantes');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  eliminar(req: Request, res: Response) {
    const {id} = req.params;
    try {
        res.send('Eliminar estudiante');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }
}

export default new EstudiantesController;