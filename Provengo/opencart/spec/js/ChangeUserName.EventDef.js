/* @Provengo summon selenium */

defineEvent(SeleniumSession, "LoginUser", function(session, e) {
    session.writeText("//*[@id='input-email']", e.email, true)
    session.writeText("//*[@id='input-password']", e.password, true)
    session.click("//*[@type='submit']");

})

defineEvent(SeleniumSession, "goToEditMyAccount", function(session, e) {
        session.click("//*[text()='Edit Account']");

})

defineEvent(SeleniumSession, "changeUserName", function(session, e) {
       session.writeText("//*[@id='input-firstname']", e.newName, true)

})

defineEvent(SeleniumSession, "clickOnContinue", function(session, e) {
        session.click("//*[text()='Continue']");
})

defineEvent(SeleniumSession, "LoginAdmin", function(session, e) {
        session.writeText("//*[@id='input-username']", e.email, true)
        session.writeText("//*[@id='input-password']", e.password, true)
        session.click("//*[@type='submit']");

})
defineEvent(SeleniumSession, "GoToCustomerPage", function(session, e) {

            session.click("//*[@id=\"content\"]/div[2]/div[1]/div[3]/div/div[3]/a");

})

defineEvent(SeleniumSession, "EditUser", function(session, e) {
            session.click("//*[@id=\"form-customer\"]/div[1]/table/tbody/tr/td[7]/div/a");

})

defineEvent(SeleniumSession, "DeactivateUser", function(session, e) {
            session.scrollToBottom(null)
            session.click("//*[@id='input-status']")
})

defineEvent(SeleniumSession, "SaveChanges", function(session, e) {

            session.scrollToBottom("//*[@id=\"nav-profile\"]/a/span")
            session.click("//*[@id=\"content\"]/div[1]/div/div/button")


})

defineEvent(SeleniumSession, "assertCustomerNameNotChanged", function(session, e) {
            session.assertText("//*[@id=\"form-customer\"]/div[1]/table/tbody/tr/td[2]",e.name)
})

defineEvent(SeleniumSession, "assertCustomerName", function(session, e) {
            session.assertText("//*[@id=\"form-customer\"]/div[1]/table/tbody/tr/td[2]",e.name)
})
