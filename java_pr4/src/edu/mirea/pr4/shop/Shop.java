package edu.mirea.pr4.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
    private final Product[] catalog;
    private final List<Product> basket;
    private final Scanner input;

    public Shop(Scanner input) {
        this.input = input;
        basket = new ArrayList<>();
        catalog = new Product[] {
            new Product("Основы Java", 750, Category.BOOKS),
            new Product("Задачи по программированию", 1100, Category.BOOKS),
            new Product("Наушники", 2400, Category.ELECTRONICS),
            new Product("Коврик для мыши", 650, Category.ELECTRONICS),
            new Product("Толстовка", 2800, Category.CLOTHES)
        };
    }

    private int readChoice() {
        if (!input.hasNextLine()) {
            return 0;
        }
        String line = input.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException error) {
            return -1;
        }
    }

    private boolean signIn() {
        System.out.println("Учебная учетная запись: vsevolod / java17");
        System.out.print("Логин: ");
        if (!input.hasNextLine()) {
            return false;
        }
        String login = input.nextLine();
        System.out.print("Пароль: ");
        if (!input.hasNextLine()) {
            return false;
        }
        String password = input.nextLine();
        return "vsevolod".equals(login) && "java17".equals(password);
    }

    private void browseCatalog() {
        Category[] categories = Category.values();
        for (int number = 0; number < categories.length; number++) {
            System.out.printf("%d. %s%n", number + 1, categories[number]);
        }
        System.out.println("Выберите категорию, 0 - назад:");
        int choice = readChoice();
        if (choice == 0) {
            return;
        }
        if (choice < 1 || choice > categories.length) {
            System.out.println("Такой категории нет");
            return;
        }

        List<Product> matches = new ArrayList<>();
        for (Product product : catalog) {
            if (product.getCategory() == categories[choice - 1]) {
                matches.add(product);
            }
        }
        for (int number = 0; number < matches.size(); number++) {
            System.out.printf("%d. %s%n", number + 1, matches.get(number));
        }
        System.out.println("Выберите товар, 0 - назад:");
        int number = readChoice();
        if (number == 0) {
            return;
        }
        if (number < 1 || number > matches.size()) {
            System.out.println("Такого товара нет");
            return;
        }
        Product selected = matches.get(number - 1);
        basket.add(selected);
        System.out.println("Добавлено: " + selected.getName());
    }

    private double printBasket() {
        double amount = 0;
        System.out.println("Корзина:");
        for (int number = 0; number < basket.size(); number++) {
            Product product = basket.get(number);
            System.out.printf("%d. %s%n", number + 1, product);
            amount += product.getPrice();
        }
        System.out.printf("К оплате: %.2f руб.%n", amount);
        return amount;
    }

    private void checkout() {
        if (basket.isEmpty()) {
            System.out.println("Сначала добавьте товары");
            return;
        }
        printBasket();
        basket.clear();
        System.out.println("Учебный заказ оформлен");
    }

    public void run() {
        if (!signIn()) {
            System.out.println("Вход не выполнен");
            return;
        }
        int choice;
        do {
            System.out.println("1. Каталог  2. Корзина  3. Оформить заказ  0. Выход");
            choice = readChoice();
            switch (choice) {
                case 1:
                    browseCatalog();
                    break;
                case 2:
                    printBasket();
                    break;
                case 3:
                    checkout();
                    break;
                case 0:
                    System.out.println("Работа завершена");
                    break;
                default:
                    System.out.println("Выберите пункт от 0 до 3");
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        Shop shop = new Shop(new Scanner(System.in));
        shop.run();
    }
}
