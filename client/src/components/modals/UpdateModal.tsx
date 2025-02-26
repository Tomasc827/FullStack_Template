import { useForm } from "react-hook-form";
import { useDataContext } from "../contexts/DataContext";
import { useEffect} from "react";


interface PropsModal {
    setIsModalOpen: (value:boolean) => void;
    currentEntity: Entity
    setUpdate: (value:number) => void;
}

interface Entity {
    id: number,
    name: string,
    imageURL: string,
}

type EntityInputs = Entity;

const UpdateModal: React.FC<PropsModal> = ({setIsModalOpen,currentEntity,setUpdate}) => {
    const {handleSubmit, formState:{errors}, register,setValue} = useForm<EntityInputs>();
    const {isDarkMode,setIsLoading,url,timeoutForError,timeoutForSuccess} = useDataContext();
    

    const postJobAd = async (data:Entity) => {
        setIsLoading(true);
        try {

            const response = await fetch(`${url}/api/entity/update/${currentEntity.id}`, {
                method:"PUT",
                headers: {
                    "Authorization": `bearer ${localStorage.getItem("token")}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
            if (response.ok) {
                await response.json();
                timeoutForSuccess("Entity updated successfully")
                setTimeout(() => {
                    setIsModalOpen(false);
                    setUpdate(prev => prev + 1)
                },1000)
            } else {
                switch (response.status) {
                    case 401: timeoutForError("Unauthenticated")
                    break;
                    case 403: timeoutForError("Unauthorized")
                    break;
                    case 400: timeoutForError("Bad Request, adjust inputs")
                }
            }
        } catch (error:unknown) {
            timeoutForError(error as string)
        } finally {
            setIsLoading(false);
        }
    }

    useEffect(() => {
      if (currentEntity) {
      setValue("name",currentEntity.name)
      setValue("imageURL",currentEntity.imageURL)
      }
    },[currentEntity])


    return ( 
    <>
    <div className="fixed flex justify-center items-center bg-black/30 min-h-screen min-w-screen z-40">
    <form onSubmit={handleSubmit(postJobAd)}  className={`pt-5 flex items-center flex-col phone:w-[20rem] tablet:w-[25rem] desktop:w-[30rem]   rounded-2xl  ${isDarkMode ? "dark-card-color text-white duration-500" : "bg-white text-black duration-500"}`}>
          <p className="text-3xl font-semibold text-center">
            Add Content
          </p>
          <label className="tomas-label outfit">Name: </label>
          <input
             className={`tomas-input ${isDarkMode ? "placeholder:text-white/50  duration-500" : "placeholder:text-gray-500 duration-500"}`}
            type="text"
            placeholder="ex: Software Engineer"
            {...register("name", {
              required: "Position is required",
              pattern: {
                value:
                  /^[a-zA-Z0-9 .,!&£$-]{2,255}$/,

                message: "Position includes only letters, numbers and symbols .,!£$-&",
              },
            })}
          ></input>
          {errors && <p className="text-sm outfit text-red-500">{errors.name?.message}</p>}

          <label className="tomas-label outfit">Image URL: </label>
          <input
            className={`tomas-input ${isDarkMode ? "placeholder:text-white/50 duration-500 " : "placeholder:text-gray-500 duration-500"}`}
            type="text"
            placeholder="ex: https://example.com/image.jpg"
            {...register("imageURL", {
                pattern: {
                    value: /^(https?:\/\/)?(www\.)?[a-zA-Z0-9-]+\.[a-zA-Z]{2,6}([\/\w .-]*)*\/?[a-zA-Z0-9_-]+\.(jpg|jpeg|png|gif|bmp)$/i,
                    message: "Please enter a valid image URL (supports jpg, jpeg, png, gif, bmp)",
                  },
            })}
          ></input>
          {errors && <p className="text-sm outfit text-red-500">{errors.imageURL?.message}</p>}

          <div className="mt-10">
          <button className="tomas-button me-10" type="submit">Post</button>
          <button type="button" className="tomas-button mb-5" onClick={() => {setIsModalOpen(false)
          }}>Close</button>
          </div>
          </form>
    </div>
    </> );
}
 
export default UpdateModal;