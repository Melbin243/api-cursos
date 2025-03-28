import express from "express";
import profesoresController from "../controllers/profesoresController";

const router = express.Router();

router.get("/", profesoresController.consultar);

router.post("/", profesoresController.ingresar);

router
  .route("/:id")
  .get(profesoresController.consultarDetalle)
  .put(profesoresController.actualizar)
  .delete(profesoresController.eliminar);

export default router;