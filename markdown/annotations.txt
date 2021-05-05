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
$ grep -nre "@" src | grep -v "author" | grep -v "param" | grep -v "return" | grep -v "Override" | grep -v "Test"
src/main/java/eu/kartoffelquadrat/bookstoreinternals/AssortmentImpl.java:12:@RestController
src/main/java/eu/kartoffelquadrat/bookstoreinternals/AssortmentImpl.java:47:    @GetMapping("/bookstore/isbns")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/AssortmentImpl.java:52:    @GetMapping("/bookstore/isbns/{isbn}")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/AssortmentImpl.java:53:    public BookDetailsImpl getBookDetails(@PathVariable("isbn") Long isbn) {
src/main/java/eu/kartoffelquadrat/bookstoreinternals/AssortmentImpl.java:58:    @PutMapping("/bookstore/isbns/{isbn}")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/AssortmentImpl.java:59:    public void addBookToAssortment(@RequestBody BookDetailsImpl bookDetails) {
src/main/java/eu/kartoffelquadrat/bookstoreinternals/RestLauncher.java:9:@SpringBootApplication
src/main/java/eu/kartoffelquadrat/bookstoreinternals/GlobalStockImpl.java:12:@RestController
src/main/java/eu/kartoffelquadrat/bookstoreinternals/GlobalStockImpl.java:53:    @GetMapping("/bookstore/stocklocations/{stocklocation}/{isbn}")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/GlobalStockImpl.java:54:    public int getStock(@PathVariable("stocklocation") String city, @PathVariable("isbn") Long isbn) {
src/main/java/eu/kartoffelquadrat/bookstoreinternals/GlobalStockImpl.java:62:    @PostMapping("/bookstore/stocklocations/{stocklocation}/{isbn}")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/GlobalStockImpl.java:63:    public void setStock(@PathVariable("stocklocation")String city, @PathVariable("isbn") Long isbn, @RequestBody Integer amount) {
src/main/java/eu/kartoffelquadrat/bookstoreinternals/GlobalStockImpl.java:72:    @GetMapping("/bookstore/stocklocations")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/GlobalStockImpl.java:78:    @GetMapping("/bookstore/stocklocations/{stocklocation}")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/GlobalStockImpl.java:79:    public Map<Long, Integer> getEntireStoreStock(@PathVariable("stocklocation") String city) {
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:14:@RestController
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:46:    @GetMapping("/bookstore/isbns/{isbn}/comments")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:47:    public Map<Long, String> getAllCommentsForBook(@PathVariable("isbn") long isbn) {
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:61:    @PostMapping("/bookstore/isbns/{isbn}/comments")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:62:    public void addComment(@PathVariable("isbn") long isbn, @RequestBody String comment) {
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:80:    @DeleteMapping("/bookstore/isbns/{isbn}/comments/{commentid}")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:81:    public void deleteComment(@PathVariable("isbn") long isbn,@PathVariable("commentid") long commentId) {
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:92:    @DeleteMapping("/bookstore/isbns/{isbn}/comments")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:93:    public void removeAllCommentsForBook(@PathVariable("isbn") long isbn) {
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:101:    @PostMapping("/bookstore/isbns/{isbn}/comments/{commentid}")
src/main/java/eu/kartoffelquadrat/bookstoreinternals/CommentsImpl.java:102:    public void editComment(@PathVariable("isbn") long isbn, @PathVariable("commentid") long commentId, @RequestBody  String updatedComment) {
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
