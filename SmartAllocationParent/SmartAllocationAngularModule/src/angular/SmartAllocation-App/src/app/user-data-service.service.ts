import { Injectable } from "@angular/core";
import { ApplicationUser } from "./applicationUser";
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class UserDataServiceService {
  public applicationUser: ApplicationUser = new ApplicationUser();

  public encodedCreds: String;

  public userName: BehaviorSubject<String> = new BehaviorSubject<String>("");

  public isAuthenticationSuccess: BehaviorSubject<
    boolean
  > = new BehaviorSubject<boolean>(false);

  constructor() {}
}
