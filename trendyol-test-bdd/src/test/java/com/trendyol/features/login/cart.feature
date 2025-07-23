Feature: Sepete ürün ekleme

  Scenario: Kullanıcı ürün detay sayfasında ürünü sepete ekler
    Given kullanıcı trendyol sitesini ziyaret eder
    And cinsiyet olarak "kadın" seçer
    And giriş yap ekranına gidilir
    And kullanıcı doğru bilgilerle giriş yapar
    When ürün detay sayfasına gider
    And sepete ekle butonuna tıklar
    Then kullanıcı sepet sayfasına yönlendirilir
    And sepette eklenen ürün görünür olmalı