Feature: Favori ürün ekleme

  Scenario: Kullanıcı ürün detay sayfasında favori butonuna tıklar
    Given kullanıcı trendyol sitesini ziyaret eder
    And cinsiyet olarak "kadın" seçer
    And giriş yap ekranına gidilir
    And kullanıcı giriş yapar
    When ürün detay sayfasına gider
    And favoriye ekle butonuna tıklar
    Then kullanıcı favoriler sayfasına gider
    And eklenen ürün favorilerde görünmelidir
