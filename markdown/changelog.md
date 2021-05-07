# Changelog

List of source code modifications, compared to the [original BookStore](https://github.com/kartoffelquadrat/BookStoreInternals).

## Launcher

New launcher added:

```java
package eu.kartoffelquadrat.bookstoreinternals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class powers up Spring and ensures the annotated controllers are detected.
 */
@SpringBootApplication
public class RestLauncher {
    public static void main(String[] args) {

        SpringApplication.run(RestLauncher.class, args);
    }
}
```

## Annotations

Search for annotations in RESTified source code. (Non spring annotations filtered)

```bash
$ grep -nre "@" src | grep -v "author" | grep -v "param" | grep -v "return" | grep -v "Override" | grep -v "Test" | cut -f3 -d":" | sed 's/\ \ \ \ //' | sort
@DeleteMapping("/bookstore/isbns/{isbn}/comments")
@DeleteMapping("/bookstore/isbns/{isbn}/comments/{commentid}")
@GetMapping("/bookstore/isbns")
@GetMapping("/bookstore/isbns/{isbn}")
@GetMapping("/bookstore/isbns/{isbn}/comments")
@GetMapping("/bookstore/stocklocations")
@GetMapping("/bookstore/stocklocations/{stocklocation}")
@GetMapping("/bookstore/stocklocations/{stocklocation}/{isbn}")
@PostMapping("/bookstore/isbns/{isbn}/comments")
@PostMapping("/bookstore/isbns/{isbn}/comments/{commentid}")
@PostMapping("/bookstore/stocklocations/{stocklocation}/{isbn}")
@PutMapping("/bookstore/isbns/{isbn}")
@RestController
@RestController
@RestController
@SpringBootApplication
public BookDetailsImpl getBookDetails(@PathVariable("isbn") Long isbn) {
public Map<Long, Integer> getEntireStoreStock(@PathVariable("stocklocation") String city) {
public Map<Long, String> getAllCommentsForBook(@PathVariable("isbn") long isbn) {
public int getStock(@PathVariable("stocklocation") String city, @PathVariable("isbn") Long isbn) {
public void addBookToAssortment(@RequestBody BookDetailsImpl bookDetails) {
public void addComment(@PathVariable("isbn") long isbn, @RequestBody String comment) {
public void deleteComment(@PathVariable("isbn") long isbn,@PathVariable("commentid") long commentId) {
public void editComment(@PathVariable("isbn") long isbn, @PathVariable("commentid") long commentId, @RequestBody  String updatedComment) {
public void removeAllCommentsForBook(@PathVariable("isbn") long isbn) {
public void setStock(@PathVariable("stocklocation")String city, @PathVariable("isbn") Long isbn, @RequestBody Integer amount) {
```

## Maven

 * Name modified:  
```<name>bookstoremanualrest</name>```
 * Parent added:  
```xml
    <!-- this parent block lets this maven project extend a prepared spring specific template.-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.0.RELEASE</version>
    </parent>
```
 * Legacy build plugin removed:  
```xml
            <!-- specify main class for exec goal -->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>1.6.0</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>java</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <mainClass>eu.kartoffelquadrat.bookstoreinternals.DesktopLauncher</mainClass>
                </configuration>
            </plugin>

            <!-- specify main class for JAR manifest-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>eu.kartoffelquadrat.bookstoreinternals.DesktopLauncher</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
```
 * Spring build plugin added:  
```xml
	    <!-- Spring specific build plugin, produces self contained JAR with default launcher class.-->
	    <plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
		<configuration>
		    <fork>true</fork>
		    <mainClass>eu.kartoffelquadrat.bookstoreinternals.RestLauncher</mainClass>
		</configuration>
		<executions>
		    <execution>
			<goals>
			    <goal>repackage</goal>
			</goals>
		    </execution>
		</executions>
	    </plugin>
```
