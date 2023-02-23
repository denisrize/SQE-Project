/* @Provengo summon selenium*/

/**
 * User opens a new browser window, login and try to change his account name.
 */
story('Login and change account name', function () {
    let s = new SeleniumSession().start('http://localhost/opencart/index.php?route=account/login&language=en-gb')
    s.loginUser({ email: 'admin@gmail.com', password: '1234' })
    s.goToEditMyAccount()
    s.changeUserName({ newName: 'Achiya2'})
    s.clickOnContinue()
})

/**
 * Admin open a new browser window, login to his account, get into users page and deactivate user account.
 */
story('Login and deactivate user account', function () {

    let s = new SeleniumSession().start('http://localhost/opencart/rize')
    s.loginAdmin({ email: 'admin', password: 'pop123' })
    s.goToCustomerPage()
    s.editUser()
    s.deactivateUser()
    s.saveChanges()
})
/**
 * Test to check the username did not change after the user tried to change it when the admin already deactivated him.
 */
story('Login as admin, go to users page and check user name after change', function () {

    on(Any('EndOfAction').and(Any({ eventName: 'SaveChanges'})), function(){
        on(Any('EndOfAction').and(Any({ eventName: 'clickOnContinue'})), function(){
            let s = new SeleniumSession().start('http://localhost/opencart/rize')
            s.loginAdmin({ email: 'admin', password: 'pop123' })
            s.goToCustomerPage()
            s.assertCustomerNameNotChanged({ name: "Achiya Eylon"})
        })})
})

/**
 * Check username did not change in any part of the different sessions, until he tries to change his name after he was already deactivated.
 */
story('Login as admin, go to users page and check user name before change', function () {

    interrupt(Any('EndOfAction').and(Any({ eventName: 'SaveChanges'})), function(){
        interrupt(Any('EndOfAction').and(Any({ eventName: 'clickOnContinue'})), function(){

            let s = new SeleniumSession().start('http://localhost/opencart/rize')
            s.loginAdmin({ email: 'admin', password: 'pop123' })
            s.goToCustomerPage()
            s.assertCustomerName({ name: "Achiya Eylon"})
})})
})

