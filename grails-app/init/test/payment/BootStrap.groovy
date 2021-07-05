package test.payment

class BootStrap {

    def paymentService
    def init = { servletContext ->

        paymentService.addPaymentConfig()
    }
    def destroy = {
    }
}
