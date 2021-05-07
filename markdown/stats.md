# Stats

<link rel="stylesheet" type="text/css" media="all" href="figure.css" />

Some metrics about changes induced by a manual RESTification of the BookStore.

## Resource layout

For convenience, a graphical illustration of the effective resource layout:

![rtl](RTL-bookstore.png#figure)

## Resources / Parameters


Full list of mapping relevant annotations:

### Signatures: Get/Put/Post/Del

```
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
```

### Parameters: Body, Path, ~~Request~~


```
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

 > Note: Intentionally no parameters of type "*request*" used for parameter mappings. (For comparability with generated code.

## Stats

### Annotations

Total annotations added:

| Annotation Type | Amount |
|---|---|
| Boilerplate | 4 |
| CRUD-Operation / method mapping | 11 |
| Parameter mapping | 17 |

### Lines added / modified / removed

| Kind | Added / Modified / Removed |
|---|---|
| New Launcher | 10 / - / - |
| Annotations | 14 / 10 / - |
| Maven configuration | 25 / 2 / 27|

 > All numbers as NLOC.


### Resource-String replications

Occurences of a resource string over scattered annotations

| Resource | String replications |
|---|---|
| ```isbn``` (left) | 13 |
| ```bookstore``` | 12 |
| ```isbns``` | 8 |
| ```stocklocation``` | 6 |
| ```comments``` | 5 |
| ```commentid``` | 4 |
| ```stocklocations``` | 4 |
| ```isbn``` (right) | 4 |

Interpretation: Highest count is **not** the root resource, but the string of a dynamic resource (placeholder for signature input parameter).


