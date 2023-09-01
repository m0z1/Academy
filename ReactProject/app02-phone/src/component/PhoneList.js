import { Component } from "react";
import PhoneInfo from "./PhoneInfo";

class PhoneList extends Component {
    render() {
        const {data,onRemove} = this.props;
        return(
            <div>
                {
                    data.map((info)=>{
                        return(
                            <PhoneInfo info= {info} onRemove={onRemove}/>
                        )
                    })
                }

             
            </div>
        )
    }
}

export default PhoneList;
