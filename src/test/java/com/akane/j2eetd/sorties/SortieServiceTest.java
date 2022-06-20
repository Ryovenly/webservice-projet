//package com.akane.j2eetd.sorties;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class SortieServiceTest {
//
//    @Autowired
//    private SortieService sortieService;
//
//    @Test
//    public void testAddSortie() {
//// Test case
//        Sortie sortie = new Sortie();
//        sortie.setName("Sortie test");
//        Sortie savedSortie = sortieService.createOrUpdate(sortie);
//// Asserts
//        assertThat(savedSortie.getId()).isNotNull();
//// Clean
//        sortieService.deleteSortieById(savedSortie.getId());
//    }
//
//    @Test
//    public void testUpdateSortie() {
//// Test case
//        Sortie sortie = new Sortie();
//        sortie.setName("Sortie test");
//        Sortie savedSortie = sortieService.createOrUpdate(sortie);
//        savedSortie.setName("Sortie test 2");
//        Sortie savedSortie2 = sortieService.createOrUpdate(savedSortie);
//// Asserts
//        assertThat(savedSortie2.getName()).isEqualTo("Sortie test 2");
//        assertThat(savedSortie.getId()).isEqualTo(savedSortie2.getId());
//// Clean
//        sortieService.deleteSortieById(savedSortie.getId());
//    }
//}
