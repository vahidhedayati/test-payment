<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
<h4><g:render template="menu"/></h4>
<payment:buttons instance="${[
        editCartUrl:g.createLink(controller:'test', action:'checkout'),


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
        shippingMethod:'Royal Mail',
        //Optional values used by paypal js file
        referenceId: 'abc123',
        description: 'My shopping items',
        customId: 'Some CustomID',
        softDescriptor: 'Some soft descriptor',

        includeItems:false,

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
]}"
/>
</body>
</html>