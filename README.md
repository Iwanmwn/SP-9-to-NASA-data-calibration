# SP-9-to-NASA-data-calibration
Programm that calibrates SP-9 data to NASA world data format using data CE-318.

Functionality of the program:
Calibrates data and save it in format of CE-318;
Saves calibration coefficients by approriate of each date and channel;
Transfering time (because of GMT+0 work format of the photometers).

Works with files: 
1) Native data file of SP9;
2) Lev 1.5 of CE-318.
Data from CE-318 can be downloaded from this site: http://aeronet.gsfc.nasa.gov/

*/
*
/*  Перевод:

Программа калибрующая данные СП-9 к мировому стандарту формата NASA по данным фотометра CE-318.

Функционал программы:
Калибрует данные и записывет их в формате записи CE-318;
Записывает соответсвующие калибровочные коэффициенты к каждой дате и каналу;
Производит перевод времени (востребовано по причине работы приборов по GMT+0).

Работает с файлами:
1) Родной файл с данными СП9
2) Файл с данными CE-318 уровня очистки 1.5
Данные фотометр CE-318 могут быть скачаны с сайта: http://aeronet.gsfc.nasa.gov/
