# WeatherApp

WeatherApp es una aplicación de línea de comandos que te permite obtener la temperatura actual y el pronóstico del tiempo para una ciudad específica utilizando la API de OpenWeatherMap.

## Requisitos

- Java JDK 8 o superior
- Clave de API de OpenWeatherMap (obtén una clave gratuita registrándote en [OpenWeatherMap](https://openweathermap.org/))

## Cómo usar

1. Clona este repositorio o descarga los archivos del proyecto.
2. Abre el proyecto en tu IDE de Java favorito.
3. Abre el archivo `WeatherApp.java`.
4. Reemplaza `"TU_CLAVE_DE_API"` con tu propia clave de API de OpenWeatherMap en la variable `API_KEY`.
5. Guarda los cambios.
6. Compila y ejecuta la aplicación.

La aplicación te pedirá que ingreses el nombre de la ciudad de la que deseas obtener el pronóstico del tiempo. Luego, te mostrará la temperatura actual y el pronóstico para los próximos 5 días naturales.

## Estructura del proyecto

El proyecto consta de tres clases principales:

- `WeatherApp.java`: La clase principal que maneja la interacción con el usuario y realiza las solicitudes a la API de OpenWeatherMap.

- `WeatherCurrentData.java`: Una clase que representa los datos de temperatura actual obtenidos de la API.

- `WeatherForecastData.java`: Una clase que representa los datos de pronóstico del tiempo obtenidos de la API.

## Contribuciones

Estamos en constante mejora para brindar una experiencia más completa a los usuarios. Si deseas contribuir a este proyecto, ¡estaremos encantados de recibir tus sugerencias y mejoras! Siéntete libre de crear un "pull request" o reportar problemas en la sección de "Issues".

## Nota

Este proyecto no tiene licencia en este momento. Si deseas utilizar o modificar el código, asegúrate de cumplir con los términos de uso de la API de OpenWeatherMap.

Gracias por usar WeatherApp y esperamos seguir mejorando la aplicación para ofrecerte una experiencia aún mejor.
