Feature: Favori ürün ekleme

  Scenario: Kullanıcı ürün detay sayfasında favori butonuna tıklar
    Given kullanıcı trendyol sitesini ziyaret eder
    And cinsiyet olarak "kadın" seçer
    And giriş yap ekranına gidilir
    And kullanıcı doğru bilgilerle giriş yapar
    When ürün detay sayfasına gider
    And favori butonuna tıklar
    Then ürün favorilere eklenmiş olmalı
