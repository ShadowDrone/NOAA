const axios = require('axios')
//Motor Cylon
const cylon = require('cylon');

const apiUrl = 'http://localhost:8080';
const boyaId = 1; //Poner aqui el id de boya
const boyaAddress = 'abc1235faaa'; //bluetooth address del robot bb8-sphero que usamos para simular

var boyaColor = 0; //Mantengo el color por fuera para no llamar al device todo el tiempo
//mi robot Handler(MCP Master Control Program)
//https://cylonjs.com/documentation/architecture/
var miBoya = cylon.robot({
    name: "Mi Boya " + boyaId,
    connections: {
        bluetooth: { adaptor: 'central', uuid: boyaAddress, module: 'cylon-ble' }
    },

    devices: {
        faro: { driver: 'bb8', module: 'cylon-sphero-ble' }
    },
    work: function (my) {

        //Hacemos que la boya registre una muestra cada 5 segundos
        //La muestra es aleatoria
        every((5).seconds(), function () {
            registrarMuestra(my);
        });

    },
    cambiarDeColor: function (color) {

        var nuevoColor = 0;
        switch (color) {
            case 'AMARILLO':
                nuevoColor = parseInt('FFFB1E', 16);
                break;
            case 'ROJO':
                nuevoColor = parseInt('FF0000', 16);
                break;
            case 'VERDE':
                nuevoColor = parseInt('00FF00', 16);
                break;
            case 'AZUL':
                nuevoColor = parseInt('0000FF', 16);
                break;

            default:
                break;
        }

        if (boyaColor != nuevoColor) {
            logInfo('Cambiando color del faro');

            this.devices.faro.color(nuevoColor);
            boyaColor = nuevoColor

            //Lo hago dar vueltas!!
            if (color == 'ROJO') {
                after(200, function () {
                    miBoya.devices.faro.spin('left', 100);
                });

                after(3000, function () {
                    miBoya.devices.faro.stop();
                });
            }
        }
        else {
            logInfo('Mantengo el color del faro')
        }


    }


});

//Inicio mi MPC handler que inicia el faro
miBoya.start();

function registrarMuestra(boya) {


    let muestra = generarMuestraAleatoria();

    logInfo('Registrando muestra aletoria de ' + muestra.altura + ' metros.');

    const options = {
        headers: {
            'Content-Type': 'application/json',
        }
    };

    axios.post(apiUrl + '/muestras', muestra, options)
        .then((res) => {
            //console.log(`statusCode: ${res.status}`)
            //console.log(res)
            logInfo('Se recibio color ' + res.data.color);
            boya.cambiarDeColor(res.data.color);

        })
        .catch((error) => {
            console.error(error)
        })
}

function generarMuestraAleatoria() {

    let muestra = {
        boyaId: boyaId,
        horarioMuestra: new Date(),
        matriculaEmbarcacion: null,
        longitud: -17.44681203,
        latitud: -113.16478854,
        altura: randomEntre(-150, 150)
    }

    return muestra;
}

function randomEntre(min, max) {
    return Math.floor(Math.random() * (max - min) + min);
}

function logInfo(info) {
    var prefix = new Date().toISOString() + " : ";
    console.log(prefix + info);
}