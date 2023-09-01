import { Component } from "react";


class Counter2  extends Component{
    state = {
        myAge : 0
    }

    plus = () => {
      this.setState(({myAge}) => ({myAge :  myAge +1 }))
        // this.setState(st => ({
        //     myAge : st.myAge +1
        // }))

    }
    minus = () => {
        this.setState(({myAge}) => ({myAge :myAge- 1}))
    }

    render() {
        return(
            <div>
                <span>나이 {this.state.myAge}</span>
                <button onClick={this.plus}>증가</button>
                <button onClick={this.minus}>감소</button>
            </div>
        )
    }
}

export default Counter2;