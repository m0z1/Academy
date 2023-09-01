import { Component } from "react";

class PhoneForm extends Component{
    state = {
        name: '',
        phone: ''
    }
    handleSubmit= (e) => {
        e.preventDefault();
        this.props.onCreate(this.state)
        this.setState({
            name: '',
            phone: ''

        })
    
    }

    handleChange= (e) => {
        console.log(e.target.name + " : "  + e.target.value);
        this.setState({
            [e.target.name]: e.target.value

        })
    
    
    
    }

    render() {
        return(
            <div>
                <form onSubmit={this.handleSubmit}>
                    <input placeholder="이름" onChange={this.handleChange} value={this.state.name} name="name"/>
                    <input placeholder="전화번호" onChange={this.handleChange} value={this.state.phone} name="phone"/>
                    <button type="submit" >등록</button>

                </form>
                <p>PhoneList</p>
            </div>
        )
    }

}
export  default PhoneForm;