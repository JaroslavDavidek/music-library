Basic info:

The formatted version is available on project wiki.
The rest interface is accessible at​: http://localhost:8080/pa165​/rest (the number of the port may vary according to your Apache Tomcat settings), the REST interface is available for Song entity only. Please note that all commands are using/producing "Content-Type:application/json".

Supported methods:

GET

http://localhost:8080/pa165​/rest/songs/{id} where {id} is the id of song you want to get, example http://localhost:8080/pa165​/rest/songs/1

http://localhost:8080/pa165​/rest/songs/title={title} where {title} is the title of song you want to get, example http://localhost:8080/pa165​/rest/songs/title=Trashed

http://localhost:8080/pa165​/rest/songs/list which will list all stored songs

http://localhost:8080/pa165​/rest/songs/{id}/listByAlbum where {id} is the id of album within which the songs will be listed, example http://localhost:8080/pa165​/rest/songs/1/listByAlbum

http://localhost:8080/pa165​/rest/songs/{id}/{type}/listByAlbum where {id} is the id of album within which the songs will be listed and {type} can be either of value 'asc' - use ascending order, or 'dsc' - descending order, example http://localhost:8080/pa165​/rest/songs/1/asc/listByAlbum

http://localhost:8080/pa165​/rest/songs/{id}/listByMusician where {id} is the id of musician, from which the songs will be listed, example http://localhost:8080/pa165​/rest/songs/1/listByMusician

http://localhost:8080/pa165​/rest/songs/{id}/from={fromYear}to={toYear}/listByMusician where {id} is the id of musician, from which the songs will be listed, {fromYear} is the lower bound of the time span (songs released within this year are also included in the result) and {toYear} is the upper bound of the time span (songs released within this year are also included in the result), example http://localhost:8080/pa165​/rest/songs/1/from=1980to=1996/listByMusician

http://localhost:8080/pa165​/rest/songs/{id}/listByGenre where {id} is the id of genre within which the songs will be listed, example http://localhost:8080/pa165​/rest/songs/1/listByGenre

CREATE

Post method to create a new entity of song. Requires to supplement a JSONObject containing specified attributes (all attributes are included in example call).
URL: http://localhost:8080/pa165​/rest/songs/create
example:$ curl -i -H "Content-Type: application/json" -X POST -d '{"title":"Back in Black", "albumId":1, "musicianId":1, "genreId":1, "bitrate":10, "albumPosition":1, "commentary":"commentary"}' http://localhost:8080/pa165/rest/songs/create

DELETE

Delete method used to remove existing entity of song. URL requires parameter of {id} which is the id of song to be deleted.
URL: http://localhost:8080/pa165​/rest/songs/{id} where {id} is the id of song, which should be deleted.
example: $ curl -i -X DELETE http://localhost:8080/pa165/rest/songs/1

PUT

Update method used to update existing entity of song. URL does not require any parameters.
URL: http://localhost:8084/pa165/rest/songs/update

example body: {"id":1,"title":"Back Black","album":{"id":3},"genre":{"id":1},"musician":{"id":5},"bitrate":256,"albumPosition":8,"commentary":"ategthe"}

example: $ curl -i -H "Content-Type: application/json" -X PUT -d '{"id":1,"title":"Back Black","album":{"id":3},"genre":{"id":1},"musician":{"id":5},"bitrate":256,"albumPosition":8,"commentary":"ategthe"}' http://localhost:8080/pa165/rest/songs/update
