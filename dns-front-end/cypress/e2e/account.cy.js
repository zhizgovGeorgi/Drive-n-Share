describe("register", () => {
    //log in first
        beforeEach(() => {
          cy.visit('http://localhost:3000/register')
          
        })
      
        it('register', () => {
            cy.get('input[name=fname]').type('Mimi')
            cy.get('input[name=lname]').type('Mem')
            cy.get('input[name=adress]').type('street')
            cy.get('input[name=email]').type('menata@abv.bg')
      cy.get('input[name=password]').type('1234')
      cy.get('.registerButton').click();
      // cy.url().should('include', 'http://localhost:3000/login');
   }) })
