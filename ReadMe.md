### Meal Point Scrapper

Scrapes meal point information from webstac.wustl.edu and displays on terminal. Set environment variables WEBSTAC_USERNAME and WEBSTAC_PASSWORD to wustl key and password respectively to get this working.

Additionally, adding the bash script to PATH and setting the first line of the script to the directory of mPoints.class would allow calling the script any directory.

Then excecuting either  
``` bash
java mPoints
```
or
``` bash
mpoint
```
on the terminal will print print the meal point information. One additional optional argument can be passed in which will indicate the timeout in milliseconds between operations, which can be useful because the servers sometime run slow and the default timeout don't allow enough time for redirections to work.
