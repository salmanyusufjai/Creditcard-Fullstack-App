import React, { Component } from 'react'
import CreditcardService from '../services/CreditcardService';
class ListCreditcardComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            cardList: []
        }
    }

    componentDidMount() {
        CreditcardService.getCards().then((res) => {
            if (undefined !== res.data.data && null !== res.data.data) {
                this.setState({ cardList: res.data.data.cardList });
            }
        })        
        .catch((e) => {
            return;
        });
    }

    refreshList = () => {
        this.componentDidMount();
    }
    formatCardNumber = (cardNumber) => {
        return cardNumber.replace(/\d{4}(?!$)/g, '$& ');
     }
    render() {
        
        return (
            <div>
                <div className="row">
                    <table className="table table-striped table-bordered">

                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Card Number</th>
                                <th>Balance</th>
                                <th>Limit</th>


                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.cardList.map(
                                    cardList =>
                                        <tr key={cardList.id}>
                                            <td> {cardList.name} </td>
                                                
                                            <td> {this.formatCardNumber(cardList.cardNumber)}</td>
                                            <td> £{cardList.balance}</td>
                                            <td> £{cardList.limit}</td>

                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }



}

export default ListCreditcardComponent
