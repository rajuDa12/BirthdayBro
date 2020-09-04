package DataClasees


class Information {
   private var name:String?=null
   private var phone:String?=null
    private var Age:String?=null
    private var Gender:String?=null
    private var imageurl: String? = null




    constructor()
    {

    }

     constructor(name: String?, phone: String?,Age:String?,Gender:String?,imageurl:String?) {
         this.name = name
         this.phone = phone
         this.Age = Age
         this.Gender = Gender
         this.imageurl = imageurl


     }


     fun getName(): String?
     {
         return name

     }



     fun setName(name: String?)
     {
         this.name=name
     }




     fun getPhone(): String?
     {
         return phone

     }



     fun setPhone(phone: String?)
     {
         this.phone=phone
     }




     fun getAge(): String?
     {
         return Age

     }


     fun setAge(Age: String?)
     {
         this.Age=Age
     }


     fun getGender(): String?
     {
         return Gender

     }

     fun setGender(Gender: String?)
     {
         this.Gender=Gender
     }


    fun getImageurl(): String? {
        return imageurl
    }

    fun setImageurl(imageurl: String?) {
        this.imageurl = imageurl
    }

 }
