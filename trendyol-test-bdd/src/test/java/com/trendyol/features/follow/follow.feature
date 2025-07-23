Feature: Satıcıyı takip etme

  Scenario: Kullanıcı ürün detay sayfasında mağazayı takip eder
    Given kullanıcı trendyol sitesini ziyaret eder
    And cinsiyet olarak "kadın" seçer
    And giriş yap ekranına gidilir
    And kullanıcı giriş yapar
    When ürün detay sayfasına gider
    And takip et butonuna tıklar
    Then mağaza başarıyla takip edilmiş olmalı
