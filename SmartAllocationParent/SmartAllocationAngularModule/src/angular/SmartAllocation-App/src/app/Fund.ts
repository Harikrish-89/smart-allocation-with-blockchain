export class Fund {
    constructor(
        public id: String,
        public name: String,
        public targetWeightPercent: Number,
        public tolerancePercent: Number,
        public minimumTradeSize: Number,
        public invstFrozenConfig: String,
        public overdraftLimitPercent: Number,
        public targetCashPercent: Number
    ) { }
}