package eu.kartoffelquadrat.bookstoreinternals;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Maximilian Schiedermeier
 */
@RestController
@CrossOrigin
public class AssortmentImpl implements Assortment {

    private static Assortment singletonReference = null;
    Map<Long, BookDetailsImpl> assortmentMap;

    /**
     * Public default constructor for frameworks that automatically initialize beans as singletons.
     */
    public AssortmentImpl() {

        this(true);
    }

    /**
     * Default constructor adds dummy data to the "in-ram DB"
     *
     * @param populate flag causes the assortment to be initialized with dummy data, if set to true
     */
    private AssortmentImpl(boolean populate) {

        assortmentMap = new LinkedHashMap<Long, BookDetailsImpl>();

        if (populate) {
            populateWithDummyData();
        }
    }

    public static Assortment getInstance() {
        if (singletonReference == null)
            singletonReference = new AssortmentImpl(true);

        return singletonReference;
    }

    @GetMapping("/bookstore/isbns")
    public Collection<Long> getEntireAssortment() {
        return assortmentMap.keySet();
    }

    @GetMapping("/bookstore/isbns/{isbn}")
    public BookDetailsImpl getBookDetails(@PathVariable("isbn") Long isbn) {
        return assortmentMap.get(isbn);
    }

    @Override
    @PutMapping("/bookstore/isbns/{isbn}")
    public void addBookToAssortment(@RequestBody BookDetailsImpl bookDetails) {
        if(assortmentMap.containsKey(bookDetails.getIsbn()))
            throw new RuntimeException("Book can not be indexed, the ISBN conflicts to an existing book.");

        assortmentMap.put(bookDetails.getIsbn(), bookDetails);
    }

    /**
     * Adds fake books to the assortmentMap, adds dummy cities with dummy stocks.
     */
    private void populateWithDummyData() {

        assortmentMap.put(Long.valueOf("9780739360385"), new BookDetailsImpl(Long.valueOf("9780739360385"), "Harry Potter and the deathly hallows", "J.K.Rowling", 5450, "Harry has finally come of age, and finally started on his final journey to defeat Voldemort for good. The Durselys are forced to go into hiding so that Voldemort’s Death Eaters will not torture them for information, and Harry sets off with Ron and Hermione on a difficult quest to find and destroy the last of Voldemort’s Horcruxes. Only once those have been destroyed, Harry knows, can Voldemort truly be killed."));
        assortmentMap.put(Long.valueOf("9780553382563"), new BookDetailsImpl(Long.valueOf("9780553382563"), "I, Robot", "Isaac Asimov", 2300, "Can you imagine living in a world where the sight of human-like robots roaming the streets isn’t unusual? That might be a world we’re going to have to adapt to soon. Science-fiction author Isaac Asimov expresses the benefits and flaws of such a fascinating world in his 1942 short story collection I, Robot."));
        assortmentMap.put(Long.valueOf("9781977791122"), new BookDetailsImpl(Long.valueOf("9781977791122"), "White nights", "Fyodor Dostoevsky", 845, "Set in 1840s St. Petersburg, the short story White Nights starts with its unnamed narrator walking around the streets of the city by himself. He states that though he has lived in the city eight years, he has only acquaintances, including an old man he frequently meets on one of his walks but never talks to."));
        assortmentMap.put(Long.valueOf("9780262538473"), new BookDetailsImpl(Long.valueOf("9780262538473"), "The invincible", "Stanislaw Lem", 2327, "In the grand tradition of H. G. Wells and Jules Verne, Stanislaw Lem's The Invincible tells the story of a space cruiser sent to an obscure planet to determine the fate of a sister spaceship whose communication with Earth has abruptly ceased. Landing on the planet Regis III, navigator Rohan and his crew discover a form of life that has apparently evolved from autonomous, self-replicating machines―perhaps the survivors of a “robot war.” Rohan and his men are forced to confront the classic quandary: what course of action can humanity take once it has reached the limits of its knowledge? In The Invincible, Lem has his characters confront the inexplicable and the bizarre: the problem that lies just beyond analytical reach."));

    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("\n **************\n * Assortment * \n **************\n");
        for(BookDetailsImpl details: assortmentMap.values())
        {
            sb.append(details);
        }
        return sb.toString();
    }
}
