package test.payment

import org.grails.plugin.payment.PaymentUser

class TestController {

    /**
     * All session variables used by plugin - needs to be reset according to your usage of plugin
     * @return
     */
    def clearAll() {
        session.user =null
        session.cart=[]
        session.cartCounter=[:]
        session.finalTotal=null
        render template:'menu'
        return
    }

    /**
     * Test checkout
     * Action index: has no variables set
     * index.gsp hard codes parameters set to
     * payment:checkout
     */
    def index() {}

    /**
     * Identical to above except
     *  paypalJSMethod:true, is declared in gsp
     */
    def indexPaypalJs() {

    }

    /**
     * Test checkout with user who has existing address
     * Action testUser is same as index instead variables come from controller
     * or a service or where ever feeding controller
     * it also attempts to load the first User entry so a user must be setup
     * payment:checkout
     */
    def testUser() {
        Map instance=[:]
        instance.editCartUrl=g.createLink(controller:'test', action:'checkout')
        //description used by doPayment in PaymentService when finalising sale
        instance.cart=[
        [id:1, name:'item 1', description: 'something 1',  currency:'GBP', listPrice:1.10],
        [id:2, name:'item 2', description: 'something 2', currency:'GBP', listPrice:1.00],
        [id:1, name:'item 1',  description: 'something 1', currency:'GBP', listPrice:1.10],
        [id:1, name:'item 1',  description: 'something 1', currency:'GBP', listPrice:1.10],
        [id:3, name:'item 3',  description: 'something 3',currency:'GBP', listPrice:0.50],
        [id:4, name:'item 4',  description: 'something 4', currency:'GBP', listPrice:0.50],
        ]
        instance.finalTotal=2.50
        instance.subTotal=12.50
        instance.user= PaymentUser?.first()

        render view:'testUser', model:[instance:instance]
    }

    /**
     * Test Form buttons alone
     */
    def testButtons() {

    }
    def testPaypalAlternativeBasic() {
        def instance  =[
                paypalJSMethod:true,  //this needs to be set to true to enable alternative paypal method
                currencyCode: 'GBP',  //defaults to PaymentConfig value if not set
                finalTotal: 0.01,
        ]
        render view:'testPaypalAlternativeBasic', model:[instance:instance]
    }

    def testPaypalAlternative() {

    }


    def testPaypalAlternativeWithItems() {

        def instance = [
                paypalJSMethod:true,  //this needs to be set to true to enable alternative paypal method

                currencyCode: 'GBP',  //defaults to PaymentConfig value if not set

                finalTotal: 10.00,  //override calculation from cart items below with your own final figure

                // when includeBreakDown is enabled below values would also be used or useful in calculation all are optional

                includeBreakDown:true,
                //if below is provided then it needs to sum up to finalTotal above
                subTotal: 5.00,
                handling: 2.00,
                taxTotal: 1.00,
                shipping: 3.00,
                shippingDiscount: 1.00,
                shippingMethod:'UPS',
                //Optional values used by paypal js file
                referenceId: 'abc123',
                description: 'My shopping items',
                customId: 'Some CustomID',
                softDescriptor: 'Some soft descriptor',

                includeItems:true,

                cart:[
                        //Tax Price for below items must match taxTotal of break down above we are making up £1.00 tax as per above
                        // Prices below must make up for subTotal above of £5
                        // Category must be a defined paypal category probably best not providing this unless you know all paypal categories
                        [id:1, name:'item 1', description: 'something 1',  currency:'GBP', listPrice:0.50,  sku:'abc123',  category:'PHYSICAL_GOODS', taxPrice: 0.20 ],
                        [id:1, name:'item 1', description: 'something 1',  currency:'GBP', listPrice:0.50,  sku:'abc123',  category:'PHYSICAL_GOODS', taxPrice: 0.20 ],
                        [id:2, name:'item 2', description: 'something 2',  currency:'GBP', listPrice:1.00,  sku:'abc124',  category:'PHYSICAL_GOODS', taxPrice: 0.20 ],
                        [id:3, name:'item 3', description: 'something 3',  currency:'GBP', listPrice:1.00,  category:'PHYSICAL_GOODS', taxPrice: 0.20 ],
                        [id:4, name:'item 4', description: 'something 4',  currency:'GBP', listPrice:1.00  ],
                        [id:5, name:'item 5', description: 'something 5',  currency:'GBP', listPrice:1.00, taxPrice: 0.20 ],
                ],

                includeAddress:true,
                address:[
                        title:'Mr',
                        firstName:'fred paypal',
                        lastName: 'Smith-Paypal',
                        line1: '1 paypal Page',
                        line2: '2 in a gsp',
                        city: 'Paypal City',
                        state:'GSP State',
                        country: 'United Kingdom',
                        countryCode: 'GB',  //should default to set value in PaymentConfig if not set
                        postcode:'se11at',
                        username: 'fredisinit@headhight.com',
                        overrideErrorHalts:true,
                        telephone:'12345',
                        emailAddress:'fredisinit@headhight.com',

                ]
        ]
        render view:'testPaypalAlternativeWithItems', model:[instance:instance]
    }

    /**
     * Test form buttons just like above but params or map sent by controller instead
     * @return
     *   //override calculation from cart items below with your own final figure
     *                   finalTotal: 1.10,
     */
    def testControllerButtons() {
        Map instance=[

                currencyCode: 'GBP',  //defaults to PaymentConfig value if not set
                editCartUrl:g.createLink(controller:'test', action:'checkout'),
                cart:[
                        [id:1, name:'item 1', description: 'something 1', currency:'GBP', listPrice:1.10],
                        [id:2, name:'item 2', description: 'something 2', currency:'GBP', listPrice:1.00],
                        [id:1, name:'item 1', description: 'something 1',  currency:'GBP', listPrice:1.10],
                        [id:1, name:'item 1', description: 'something 1',  currency:'GBP', listPrice:1.10],
                        [id:3, name:'item 3', description: 'something 3',  currency:'GBP', listPrice:0.50],
                        [id:4, name:'item 4', description: 'something 4', currency:'GBP', listPrice:0.50],
                ],
                address:[
                        title:'Mr',
                        firstName:'fred',
                        lastName: 'Smith',
                        line1: '1aaaqqa Long lane',
                        line2: 'Bigaaa street',
                        city: 'London',
                        state:'x',
                        country: 'United Kingdom',
                        countryCode: 'GB',  //should default to set value in PaymentConfig if not set
                        postcode:'se11at',
                        telephone:'12345',
                        emailAddress:'fred@example.fred.smith.a11.a10.com',
                        username: 'fred@example.fred.smith.a11.a10.com',
                        overrideErrorHalts:true,
                ]
        ]

        render view:'testControllerButtons', model:[instance:instance]
    }


    /**
     * Test sessions set before calling plugin checkout method of plugin
     * This loads cart as per your session values
     * @return plugin payment checkout method
     */
    def sessionTest() {
        session.cart = [
                [id:1, name:'session item 1',  description: 'something 1',  currency:'GBP', listPrice:1.10],
                [id:1, name:'session item 1', description: 'something 1',  currency:'GBP', listPrice:1.10],
                [id:1, name:'session item 1', description: 'something 1',  currency:'GBP', listPrice:1.10],
                [id:2, name:'session item 2', description: 'something 2',  currency:'GBP', listPrice:1.00],
                [id:3, name:'session item 3', description: 'something 3',  currency:'GBP', listPrice:1.10],
                [id:4, name:'session item 4', description: 'something 4',  currency:'GBP', listPrice:1.10],
                [id:3, name:'session item 3', description: 'something 3',  currency:'GBP', listPrice:0.50],
                [id:4, name:'session item 4', description: 'something 4',  currency:'GBP', listPrice:0.50],
        ]
        session.cartCounter=[:]
        Map cartCounter=[:]
        List<Long> doneIds = []
        List items =  session.cart
        items?.sort { a, b -> a?.id <=> b?.id }.each { item ->
            Integer qty = items?.findAll{it?.id == item?.id}?.size() ?: 1
            Long id = item?.id as Long
            BigDecimal itemTotal = item?.listPrice * qty
            if (id && !doneIds.contains(id as Long)) {
                def itemInfo = [:]
                itemInfo.item=item
                itemInfo.qty=qty
                itemInfo.itemTotal=itemTotal
                cartCounter[item.id]=qty
                cartCounter[item.id]=qty
            }
        }
        session.cartCounter = cartCounter
        redirect(controller:'payment', action:'checkout')
    }

}
