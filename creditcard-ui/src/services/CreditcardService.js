import http from '../util/http-common';

class CreditCardService {

    getCards() {
        return http.get('/getAllCards', { data: null });
    }

    addCreditCard(cardDetail) {
        return http.post('/addCard', cardDetail);
    }

}

export default new CreditCardService()

