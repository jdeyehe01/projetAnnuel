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

    models.Conference.belongsToMany(models.Locate , {
      as: 'locates',
      through: 'ConferenceLocate',
      foreignKey: 'conference_id'
    });

    models.Conference.hasMany(models.Budget, {
      as: 'budgets'
    });

    models.Conference.hasMany(models.Task, {
      as: 'tasks'
    });

    models.Conference.hasMany(models.Presentation, {
      as: 'presentations'
    });

}
