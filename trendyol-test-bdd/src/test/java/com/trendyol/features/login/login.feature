Feature: Trendyol giriş işlemi

  Scenario: Kullanıcı kullanıcı önce yanlış, sonra doğru bilgilerle giriş yapar
    Given kullanıcı trendyol sitesini ziyaret eder
    And cinsiyet olarak "kadın" seçer
    And giriş yap ekranına gidilir
    When kullanıcı hatalı bilgilerle giriş yapar
    Then hata mesajı görünmelidir
    When kullanıcı doğru bilgilerle giriş yapar
    Then Hesabım menüsü görünmelidir