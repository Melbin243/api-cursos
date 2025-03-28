import { Request, Response } from "express";

class CursosController {
  constructor() {}

  consultar(req: Request, res: Response){
    try {
        res.send('Consultar cursos');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  ingresar(req: Request, res: Response) {
    try {
        res.send('Ingresar cursos');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  consultarDetalle(req: Request, res: Response){
    const {id} = req.params;
    try {
        res.send('Consultar curso');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  actualizar(req: Request, res: Response){
    const {id} = req.params;
    try {
        res.send('Actualizar curso');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }

  eliminar(req: Request, res: Response) {
    const {id} = req.params;
    try {
        res.send('Eliminar curso');
    } catch(err) {
        if(err instanceof Error)
            res.status(500).send(err.message);
    }
  }
}

export default new CursosController;