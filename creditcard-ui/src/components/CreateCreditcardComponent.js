
import React, { Component } from "react"
import { ToastContainer, toast } from "react-toastify";
import CreditcardService from "../services/CreditcardService";
import ListCreditcardComponent from "./ListCreditcardComponent";

const MessageConstant = {
    EMPTY_INVALID_NAME: 'Card name should not be empty',
    INVALID_CARD_NUMBER: 'Card Number should not be empty or more then 19 characters',
    INVALID_CARD_LIMIT: 'Card limit should not be empty and should be valid number between 0 to 9',
    CARD_ADDED_SUCCESSFULLY: 'Card Detail save successfully',
    NETWORK_ERROR: 'Network Error. Please try after some time or connect with administrator'

};
class CreateCreditcardComponent extends Component {
    constructor(props) {
        super(props)
        this.cardListRef = React.createRef();
        this.state = {
            name: '',
            cardNumber: '',
            limit: '',
            isError: {
                name: '',
                cardNumber: '',
                limit: ''
            }
        }

        this.addCard = this.addCard.bind(this);
    }



    addCard = (e) => {

        e.preventDefault();
        if (!this.validate()) {
            return;
        }


        let cardDetails = { name: this.state.name, cardNumber: this.state.cardNumber, limit: this.state.limit };
        CreditcardService.addCreditCard(cardDetails).then(res => {
            this.cardListRef.current.refreshList();
            toast.success(res.data.message);
        })
            .catch((e) => {
                if(e.message === 'Network Error') 
                {
                    toast.error(MessageConstant.NETWORK_ERROR);
                    return;
                }
                const errors = e.response.data.message;
                toast.error(errors);
                return;
            });

    }

    formValChange = e => {
        e.preventDefault();
        const { name, value } = e.target;
        let isError = { ...this.state.isError };
        switch (name) {
            case "name":
                isError.name =
                    value.length === 0 ? MessageConstant.EMPTY_INVALID_NAME : "";
                break;
            case "cardNumber":
                isError.cardNumber =
                    value.length === 0 ? MessageConstant.INVALID_CARD_NUMBER : "";
                break;
            case "limit":
                isError.limit =
                    value.length === 0 ? MessageConstant.INVALID_CARD_LIMIT : "";
                break;
            default:
                break;
        }
        this.setState({
            isError,
            [name]: value
        })
    };

    validate = () => {
        let validityStatus = true
        if (this.state.name.length === 0) {
            validityStatus = false
            this.state.isError.name = MessageConstant.EMPTY_INVALID_NAME
        }
        if (this.state.cardNumber.length === 0) {
            validityStatus = false
            this.state.isError.cardNumber = MessageConstant.INVALID_CARD_NUMBER
        }
        if (this.state.limit.length === 0) {
            validityStatus = false
            this.state.isError.limit = MessageConstant.INVALID_CARD_LIMIT
        }
        this.setState({ isError: this.state.isError })
        return validityStatus
    };

    render() {
        const { isError } = this.state;
        return (

            <div><ToastContainer />

                <br></br>
                <div>
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h2 className="text-left">Credit Card System</h2>

                            <h3 className="text-left">Add</h3>
                            <div className="card-body" style={{ textAlign: "left" }}>
                                <form noValidate>
                                    <div className="form-group">
                                        <label> Name: </label>
                                        <input placeholder="Name" name="name"
                                            className={isError.name.length > 0 ? "is-invalid form-control" : "form-control"}
                                            value={this.state.name} onChange={this.formValChange} />
                                        {isError.name.length > 0 && (
                                            <span className="invalid-feedback">{isError.name}</span>
                                        )}

                                    </div>
                                    <div className="form-group">
                                        <label> Card Number: </label>
                                        <input placeholder="Card Number" name="cardNumber" type="number"
                                            className={isError.cardNumber.length > 0 ? "is-invalid form-control" : "form-control"}

                                            value={this.state.cardNumber} onChange={this.formValChange} />
                                        {isError.cardNumber.length > 0 && (
                                            <span className="invalid-feedback">{isError.cardNumber}</span>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <label> Limit: </label>
                                        <input placeholder="Card Limit" name="limit" type="number"
                                            className={isError.limit.length > 0 ? "is-invalid form-control" : "form-control"}

                                            value={this.state.limit} onChange={this.formValChange} />
                                        {isError.limit.length > 0 && (
                                            <span className="invalid-feedback">{isError.limit}</span>
                                        )}
                                    </div>
                                    <br></br>
                                    <button className=" form-group btn btn-success" onClick={this.addCard}>Add</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
                <br></br>
                <div style={{ textAlign: "left" }}>
                    <h3>Existing Cards</h3>
                </div>
                <div >
                    <ListCreditcardComponent ref={this.cardListRef} />
                </div>
            </div>
        )
    }
}

export default CreateCreditcardComponent