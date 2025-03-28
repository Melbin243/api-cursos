import { Request, Response } from "express";

class ProfesoresController {
  constructor() {}

  consultar(req: Request, res: Response){
    try {
        res.send('Consultar profesores');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  ingresar(req: Request, res: Response) {
    try {
        res.send('Ingresar profesores');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  consultarDetalle(req: Request, res: Response){
    const {id} = req.params;
    try {
        res.send('Consultar profesor');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  actualizar(req: Request, res: Response){
    const {id} = req.params;
    try {
        res.send('Actualizar profesor');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  eliminar(req: Request, res: Response) {
    const {id} = req.params;
    try {
        res.send('Eliminar profesor');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }
}

export default new ProfesoresController;