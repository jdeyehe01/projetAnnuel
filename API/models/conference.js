module.exports = function (sequelize, DataTypes) {
    const Conference = sequelize.define('Conference', {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        name: {
            type: DataTypes.STRING,
            allowNull: false
        },
        dateDebut: {
            type: DataTypes.DATE,
            allowNull: false
        },
        adresse: {
            type: DataTypes.STRING,
            allowNull: false
        },
        codePostal: {
            type: DataTypes.INTEGER,
            allowNull: false
        },
        ville: {
            type: DataTypes.STRING,
            allowNull: false
        },
        heureDebut: {
            type: DataTypes.STRING,
            allowNull: false
        },
        description: {
            type: DataTypes.STRING,
            allowNull: false
        }
    }, {
        paranoid: false,
        underscored: true,
        freezeTableName: true
    });
    Conference.associate = _associate;
    return Conference;
};



function _associate(models) {
  models.Conference.belongsTo(models.User);


    models.Conference.belongsToMany(models.Guest, {
      as: 'guests',
      through: 'GuestConference',
      foreignKey: 'conference_id'
    });

}
