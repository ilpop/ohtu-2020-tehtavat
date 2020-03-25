Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new user is selected
        When a valid username "uusikayttaja" and password "salainensalainen1" and matching password confirmation are entered
        Then a new user is created

    Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When a too short username "ai" and password "salainensana1" and matching password confirmation are entered
        Then user is not created and error "username should have at least 3 characters" is reported

    Scenario: creation fails with correct username and too short password
        Given command new user is selected
        When a valid username "miisa" and too short password "p" and matching password confirmation are entered
        Then user is not created and message is reported

    Scenario: creation fails when password and password confirmation do not match
        Given command new user is selected
        When a valid username "ilkka" and valid password "salasana123" and unmatching password confirmation "salasana321" are entered
        Then user is not created and error message is given

    Scenario: user can login with successfully generated account
        Given user with username "lea" with password "salainen1" is successfully created
        And login is selected
        When a successfully generated username "lea" and password "salainen1" and matching password confirmation are entered
        Then user is logged in
        

    Scenario: user can not login with account that is not successfully created
        Given user with username "aa" and password "bad" is tried to be created
        And login is selected
        When a unsuccessfully created username "aa" and password "bad" are entered
        Then user is not logged in and error message is given
