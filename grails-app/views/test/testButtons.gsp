<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
<h4><g:render template="menu"/></h4>
<payment:buttons instance="${[
        finalTotal: 1.00,  //override calculation from cart items below with your own final figure
        currencyCode: 'GBP',  //defaults to PaymentConfig value if not set
        editCartUrl:g.createLink(controller:'test', action:'checkout'),
        address:[
                title:'Mr',
                firstName:'fred from gsp',
                lastName: 'Smith in GSP',
                line1: '1 a GSP Page',
                line2: 'set a by gsp',
                city: 'GrailsServerPage',
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