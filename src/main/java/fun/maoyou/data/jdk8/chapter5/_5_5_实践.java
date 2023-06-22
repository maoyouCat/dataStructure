package fun.maoyou.data.jdk8.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _5_5_实践 {
    static List<Transaction> transactions;

    static {
        Trader 小强 = new Trader("小强", "深圳");
        Trader 小洪 = new Trader("小洪", "上海");
        Trader 小兰博 = new Trader("小兰博", "长沙");
        Trader 小明 = new Trader("小明", "深圳");

        transactions = Arrays.asList(new Transaction(小兰博, 2011, 400),
                new Transaction(小明, 2012, 700),
                new Transaction(小明, 2012, 700),
                new Transaction(小明, 2012, 700),
                new Transaction(小洪, 2012, 950)
        );

    }

    public static void main(String[] args) {
        transactions.stream().filter(e -> e.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue));

        transactions.stream().map(e -> e.getTrader().getCity()).distinct();

        transactions.stream().filter(e -> e.getTrader().getCity().equals("深圳")).sorted(Comparator.comparing(e -> e.getTrader().getName()));

        transactions.stream().map(e -> e.getTrader().getName());

        transactions.stream().filter(e -> e.getTrader().getCity().equals("深圳")).map(e -> e.getValue()).reduce((a, b) -> a + b);


        transactions.stream().map(Transaction::getValue).reduce(Integer::max);


    }
}
