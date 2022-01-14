# Book Store (Manually RESTified)

<link rel="stylesheet" type="text/css" media="all" href="markdown/figure.css" />

Manually RESTified server side java code of a fake Book-Store for academic case studies.
This implementation imitates the expected output of a Spring-based RESTify concern. All controllers act as proxy methods.

## About

A RESTful version of the bookstore. Added controllers act as proxies. Only Proxies, a Spring specific launcher class and a Spring specific ```pom.xml``` were added.  
This software uses the original BookStore as maven dependency. To use this RESTful service, you must therefore first [compile the BookStore internals in *package* mode](https://github.com/kartoffelquadrat/BookStoreInternals#invokation).

### REST interface layout

 * [Full API specification as ARC](markdown/bookstore.arc)
 * Resource layout visualization:  
![RTL](markdown/RTL-bookstore.png#figure)

 > Note: For comparability to TouchCORE's *RESTify* concern, this implementation contains no **query string parameters** (e.g.: ```http://url?name=value```, ```@RequestParam String name```)  
Instead of a (optional for G, mandatory for Po) request parameter ```isbn``` (to filter on the stocklocation resource), an additional subresource has been added. This design is disputable, but pragmatic for the purpose if this demo application.

## Usage

On execution, the bookstore will simply print a listing of persisted initial dummy data.

 1. Get the sources:  
```git clone https://github.com/kartoffelquadrat/BookStoreManualRestify.git```

 2. Compile the project with maven. This provides you with a self contained ```jar``` in the ```target```folder:  
```mvn clean package```
 
 3. Run the jar:  
 ```java -jar target/BookStore.jar```

## Contact / Pull Requests

 * Author: Maximilian Schiedermeier ![email](markdown/email.png)
 * Github: Kartoffelquadrat
 * Webpage: https://www.cs.mcgill.ca/~mschie3
 * License: [MIT](https://opensource.org/licenses/MIT)

