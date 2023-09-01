
const mydata = {
    "myfood" : [
    
    {
            name: "햄버거",
            price : "오천원",
            buy : "배달"
    },
    {
        name: "도시락",
        price : "육천원",
        buy : "포장"
    },
    {
        name: "커피",
        price : "삼천원",
        buy : "매장"

    }

    ]
}

const myphone = {
    "phone" : [
        {
            name : "아이폰",
            ram : "6gb",
            touch : "yes",
            face : "yes"
        },
        {
            name : "갤럭시",
            ram : "12gb",
            touch : "yes",
            face : "yes"
        },
        {
            name : "샤오미",
            ram : "16gb",
            touch : "yes",
            face : "NO"
        },
        {
            name : "pixel",
            ram : "8gb",
            touch : "yes",
            face : "yes"
        }
    ]
}

function Food(){
    return(
        <div>
            <h1>Food 컴포넌트(음식)</h1>
            {
                mydata.myfood.map((food,i) =>{
                    return(
                    <div key={i}>
                        이름 :  {food.name}<br/>
                        가격 : {food.price}<br/>
                        방법 : {food.buy}<br/><br/>
                    </div>
                    )
                })
            }
            <h1>myPhone 컴포넌트(폰정보)</h1>{
                myphone.phone.map((phone, i) =>{
                    return(
                        <div key={i}>
                            이름 : {i} : {phone.name}<br/>
                            Ram : {i} : {phone.ram}<br/>
                            touch : {i} : {phone.touch}<br/>
                            face : {i} : {phone.face}<br/><br/>
                        </div>
                    )
                })
            }
        </div>

    )
}



export default Food;