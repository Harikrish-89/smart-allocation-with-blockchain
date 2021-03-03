import { Fund } from "./Fund";

export class Sector {

    constructor(
        public id: String,
        public name: String,
        public funds: Fund[]
    ) { }
}