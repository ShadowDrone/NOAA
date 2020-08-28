var express = require("express");
const bodyParser = require('body-parser');
const cylon = require("cylon");
var app = express();

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(express.static('public'));

//Motor Cylon
var Cylon = require('cylon');

//Robot info
var robotData = {
    nombre: '',
    color: ''
};

//mi robot Handler(MCP Master Control Program)
//https://cylonjs.com/documentation/architecture/
var miRobotito = Cylon.robot({

    connections: {
        bluetooth: { adaptor: 'central', uuid: 'f820dfeb30ba', module: 'cylon-ble' }
    },

    devices: {
        bb8: { driver: 'bb8', module: 'cylon-sphero-ble' }
    },
    work: function (my) {
        /* ACA Podemos hacer que ejecute cualquier cosa al momento del start()
        para pararlo llamar luego al halt()*/

    },

    //Funcion customizada
    cambiarDeColor: function (color) {

        var nuevoColor = parseInt(color, 16);
        //console.log("Nuevo color hex " + color);
        //console.log("Nuevo color int " + nuevoColor);
        this.devices.bb8.color(nuevoColor);
    }

});

//Inicio mi MPC handler que inicia el bb8
miRobotito.start();

app.get("/robot", (req, res, next) => {
    res.json(robotData);
});

app.post('/robot', function (req, res) {

    if (!req.body.nombre || !req.body.color) {
        respuesta = {
            error: true,
            codigo: 400,
            mensaje: 'El campo nombre y color son requeridos'
        };
        res.status(400).send(respuesta);
        return;
    }
    //Actualizo la info del robot
    robotData = {
        nombre: req.body.nombre,
        color: req.body.color
    };
    //Genero el response
    respuesta = {
        error: false,
        codigo: 200,
        mensaje: robotData.nombre + ' le cambio el color a ' + robotData.color
    };
    miRobotito.cambiarDeColor(robotData.color);

    //Devuelvo todo ok
    res.send(respuesta);
});

/*
function finalizar() {
    // all the stuff you want to happen after that pause
    miRobotito.halt();

}
*/

app.listen(8080, () => {
    console.log("Server running on port 8080");
});