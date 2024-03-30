package ru.netology.java12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();
    Product item1 = new Product(11, "хлеб", 40);
    Product item2 = new Product(222, "булка", 30);
    Product item3 = new Product(3, "картошка", 20);

    @BeforeEach
    public void setup() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
    }

    @Test
    public void removeByExistentId() {
        repo.removeById(3);

        Product[] expected = {item1, item2};
        Product[] actual = repo.getItems();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByNonExistentId() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(11212411);
        });
    }

    @Test
    public void AddNonExistentProduct() {
        Product item4 = new Product(1155, "хлеб", 40);
        repo.add(item4);

        Product[] expected = {item1, item2, item3, item4};
        Product[] actual = repo.getItems();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void AddExistentProduct() {
        Product item4 = new Product(11, "хлеб", 40);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item4);
        });
    }
}