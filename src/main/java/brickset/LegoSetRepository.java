package brickset;

import repository.Repository;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }


    /**
     * Print out the themes of LEGO sets without repetition and alphabetically ordered.
     */
    public void printLegoSetsThemes() {
                getAll().stream().
                map(LegoSet::getTheme).
                        distinct().
                        sorted(Comparator.nullsFirst(Comparator.naturalOrder())).
                forEachOrdered(System.out::println);
    }

    /**
     * Return the sum of the pieces where the theme is specified.
     *
     * @param theme a Lego set theme.
     * @return the sum of the pieces where the theme is specified.
     */
    public Long sumLegoSetsWithTheme(String theme) {
        return getAll().stream().
                filter(legoSet -> legoSet.getTheme() != null && legoSet.getTheme().equals(theme)).
                mapToLong(LegoSet::getPieces).
                boxed().
                reduce(0L,(a,b)->a+b);

    }

    /**
     * Return a LegoSet object with the maximum pieces.
     *
     * @return LegoSet object with the maximum pieces.
     */
    public LegoSet maxLegoSetByPieces() {
        return getAll().stream().
                max(Comparator.comparingInt(LegoSet::getPieces)).
                get();

    }

    /**
     * Return the sum of the pieces where the theme is null.
     *
     * @return the sum of the pieces where the theme is null.
     */
    public Long sumLegoSetsWithTheme() {
        return getAll().stream().
                filter(legoSet -> legoSet.getTheme().length() == 0).
                mapToLong(LegoSet::getPieces).
                boxed().
                reduce(0L,(a,b)->a+b);

    }
    /**
     * Print out the first lim number of LEGO sets number alphabetically ordered.
     *
     * @param lim is the limit of the numbers.
     */
    public void printFirstLegoSetsNumberByLimit(long lim) {
         getAll().stream().
                 map(LegoSet::getNumber).
                 sorted(Comparator.nullsFirst(Comparator.naturalOrder())).
                 limit(lim).
                 forEachOrdered(System.out::println);

    }

    /**
     * Print out the themes of LEGO sets with lower case.
     */
    public void printLowerCaseLegoSetsThemes() {
        getAll().stream().
                map(LegoSet::getTheme).
                map(String::toLowerCase).
                forEachOrdered(System.out::println);

    }

    //second part

    /**
     * Return with true if the given theme is exist in the LEGO set, and false otherwise.
     * @param theme a Lego set theme.
     * @return with true if the given theme is exist in the LEGO set, and false otherwise.
     */
    public boolean isThemeExist(String theme)
    {
        return getAll().stream().
            map(LegoSet::getTheme).
            anyMatch(s -> s.equals(theme));
    }

    /**
     * Print out the first character in the name of LEGO sets.
     */
    public void printFirstCharacterOfName()
    {
         getAll().stream().
                map(LegoSet::getName).
                flatMap(s -> Stream.of(s.charAt(0)))
                 .forEach(System.out::println);
    }
    /**
     * Return with the minimal number of pieces and if it cannot be calculated then return with {@code Integer.MAX_VALUE} otherwise.
     * @return with the minimal number of pieces and if it cannot be calculated then return with {@code Integer.MAX_VALUE} otherwise.
     */
    public int minPiece()
    {
        return getAll().stream().
                map(LegoSet::getPieces).
                reduce(Integer.MAX_VALUE,Integer::min);
    }


    public static void main(String[] args)
    {
        var repository = new LegoSetRepository();
        repository.printLegoSetsThemes();
        System.out.println(repository.sumLegoSetsWithTheme("Bionicle"));
        System.out.println(repository.sumLegoSetsWithTheme());
        System.out.println(repository.maxLegoSetByPieces().getName());
        repository.printFirstLegoSetsNumberByLimit(5);
        repository.printLowerCaseLegoSetsThemes();

        //second part
        System.out.println(repository.isThemeExist("ASDASDASDASD"));
        repository.printFirstCharacterOfName();
        System.out.println(repository.minPiece());



    }

}
