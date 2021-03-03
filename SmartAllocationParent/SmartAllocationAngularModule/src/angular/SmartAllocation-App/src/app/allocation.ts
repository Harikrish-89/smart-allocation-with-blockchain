import { Sector } from "./sector";

export class Allocation {
  constructor(
    public  id : String,
    public clientOrg: String,
    public  sector : Sector
  ) {}
}
