describe("register", () => {
    //log in first
        beforeEach(() => {
          cy.visit('http://localhost:3000/register')
          
        })
      
        it('register', () => {
            cy.get('input[name=fname]').type('KOsta')
            cy.get('input[name=lname]').type('Mosta')
            cy.get('input[name=adress]').type('street')
            cy.get('input[name=email]').type('mostata@abv.bg')
      cy.get('input[name=password]').type('1234')
      cy.get('input[name=policycheck]').click()
      cy.get('.registerButton').click();
      cy.url().should('include', 'http://localhost:3000/login');
   }) })
